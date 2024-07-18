import http from 'k6/http';
import { check } from 'k6';
import { Rate } from 'k6/metrics';

const failureRate = new Rate('check_failure_rate');

export default function () {
  const res = http.get('http://your-app.com');

  check(res, {
    'status is 200': (r) => r.status === 200,
  }) || failureRate.add(1);
}

// k6 run --vus 10 --duration 30s test.js