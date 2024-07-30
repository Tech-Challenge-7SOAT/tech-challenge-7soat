import http from 'k6/http';
import { check } from 'k6';
import { Rate } from 'k6/metrics';

const failureRate = new Rate('check_failure_rate');

export default function () {
  const res = http.get('http://127.0.0.1:57854');

  check(res, {
    'status is 200': (r) => r.status === 200,
  }) || failureRate.add(1);
}
