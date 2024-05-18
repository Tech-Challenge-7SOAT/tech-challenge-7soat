# docker build . -f PostgresDockerfile -t postgres:7soat
# docker run -d -p 5432:5432 --name soat7_db postgres:7soat
FROM postgres:latest
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 7soat
ENV POSTGRES_DB 7soat