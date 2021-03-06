package io.srinskit.adder;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

import io.vertx.micrometer.*;
import io.micrometer.core.instrument.*;
import io.vertx.micrometer.backends.BackendRegistries;

public class AdderServiceImpl implements AdderService {
	public AdderServiceImpl(Vertx vertx) {
	}

	@Override
	public void operate(Integer a, Integer b, Handler<AsyncResult<Integer>> resultHandler) {
		System.out.println("Called me");
		MeterRegistry registry = BackendRegistries.getDefaultNow();
		Counter counter = registry.counter("Service.requestCount", "service", "adder");
		counter.increment(1);
		resultHandler.handle(Future.succeededFuture(a + b));
	}
}