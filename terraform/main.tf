resource "kubernetes_namespace" "fastfood-namespace" {
    metadata {
        name = "fastfood-api"
    }
}

resource "kubernetes_deployment" "fastfood-deployment" {
  metadata {
    name = "fastfood-api"
    namespace = kubernetes_namespace.fastfood-namespace.metadata[0].name
    labels = {
      app = "fastfood"
    }
  }
  spec {
    replicas = 2
    selector {
      match_labels = {
        app = "fastfood"
      }
    }
    template {
      metadata {
        labels = {
          app = "fastfood"
        }
      }
      spec {
        container {
          name  = "fastfood-api"
          image = "mariusso/fastfood-api:0.0.3"
          port {
            container_port = 8080
          }
          env {
            name  = "DB_HOST"
            value = "${kubernetes_service.postgres-service.metadata[0].name}.${kubernetes_namespace.fastfood-namespace.metadata[0].name}.svc.cluster.local"
          }
          env {
            name  = "DB_PORT"
            value = "5432"
          }
          env {
            name  = "DB_NAME"
            value = "mydb"
          }
          env {
            name  = "DB_USER"
            value = "root"
          }
          env {
            name  = "DB_PASSWORD"
            value = "root"
          }
          resources {
            limits = {
              cpu    = "0.5"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "50Mi"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_horizontal_pod_autoscaler" "fastfood-hpa" {
  metadata {
    name = "fastfood-api"
    namespace = kubernetes_namespace.fastfood-namespace.metadata[0].name
  }
  spec {
    max_replicas = 10
    min_replicas = 1

    scale_target_ref {
      kind        = "Deployment"
      name        = "fastfood-api"
      api_version = "apps/v1"
    }
    target_cpu_utilization_percentage = 75
  }
}

resource "kubernetes_deployment" "postgre-deployment" {
  metadata {
    name      = "postgres-fastfood-api"
    namespace = kubernetes_namespace.fastfood-namespace.metadata[0].name
    labels = {
      app = "postgres"
    }
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "postgres"
      }
    }
    template {
      metadata {
        labels = {
          app = "postgres"
        }
      }
      spec {
        container {
          name  = "postgres"
          image = "postgres:16-alpine"
          env {
            name  = "POSTGRES_DB"
            value = "mydb"
          }
          env {
            name  = "POSTGRES_USER"
            value = "root"
          }
          env {
            name  = "POSTGRES_PASSWORD"
            value = "root"
          }
          port {
            container_port = 5432
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "fastfood-service" {
  metadata {
    name      = "fastfood-api"
    namespace = kubernetes_namespace.fastfood-namespace.metadata[0].name
  }
  spec {
    selector  = {
      app = kubernetes_deployment.fastfood-deployment.spec[0].template[0].metadata[0].labels.app
    }
    port {
      port        = 80
      target_port = 8080
    }
  }
}

resource "kubernetes_service" "postgres-service" {
  metadata {
    name      = "postgres-fastfood-api"
    namespace = kubernetes_namespace.fastfood-namespace.metadata[0].name
  }
  spec {
    selector = {
      app = "postgres"
    }
    port {
      port        = 5432
      target_port = 5432
    }
  }
}