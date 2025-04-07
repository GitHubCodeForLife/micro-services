package com.programming.techie.resilience4j;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Function;

public class DemoResilience4J {
    private static void settingLogLevel() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = context.getLogger("ROOT");
        rootLogger.setLevel(Level.DEBUG);
    }
    public static void main(String[] args) {
        settingLogLevel();
        Service service = new Service();
//        demoCircuitBreaker(service);
//        demoRateLimiter(service);
//        demoRetry(service);
          demoTimeLimit(service);
    }

    private static void demoTimeLimit(Service service) {
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofMillis(500))  // Maximum time to allow execution (500 milliseconds)
//                .build();
//
//        TimeLimiter timeLimiter = TimeLimiter.of("timeLimiter", timeLimiterConfig);
//        Function<Integer, Integer> decorated = TimeLimiter.decorateFutureSupplier(timeLimiter,
//                () -> service::process);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>> Processing  " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//            try {
//                int result = decorated.apply(i);
//                System.out.println("Result : " + result);
//            } catch (Exception ignore) {
//            }
//        }
    }
    private static void demoRetry(Service service) {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(2)  // Max number of retry attempts
                .waitDuration(Duration.ofMillis(1000))  // Time to wait between retries (1 second)
                .retryExceptions(Exception.class)  // Retry only on specific exceptions
                .build();

        Retry retry = Retry.of("retry", retryConfig);
        Function<Integer, Integer> decorated = Retry.decorateFunction(retry, service::process);
        for (int i = 0; i < 10; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>> Processing  " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            try {
                int result = decorated.apply(i);
                System.out.println("Result : " + result);
            } catch (Exception ignore) {
//                ignore.printStackTrace();
                //print state of circuit breaker
                System.out.println("Circuit Breaker State <Exception>: " + retry.getName());
            }
        }
    }


    private static void demoRateLimiter(Service service) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(4)  // Maximum number of permits (requests) allowed per period
                .limitRefreshPeriod(Duration.ofSeconds(1))  // Time period (1 second)
                .timeoutDuration(Duration.ofMillis(500))  // Timeout for acquiring a permit
                .build();
        RateLimiterRegistry registry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiter = registry.rateLimiter("my");
        Function<Integer, Integer> decorated
                = RateLimiter.decorateFunction(rateLimiter, service::process);

        for (int i = 0; i < 10; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>> Processing  " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            try {
                int result = decorated.apply(i);
                //print state of circuitBreaker
                System.out.println("Result : " + result);
            } catch (Exception ignore) {
//                ignore.printStackTrace();
                //print state of circuit breaker
                System.out.println("Circuit Breaker State <Exception>: " + rateLimiter.getName());
            }
        }
    }

    private static void demoCircuitBreaker(Service service) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)  // 50% failure rate will trigger the circuit breaker
                .slidingWindowSize(5) // Check last 5 calls for failure rate calculation
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofMillis(5000))  // Set the wait time for 5 seconds
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
        Function<Integer, Integer> decorated = CircuitBreaker
                .decorateFunction(circuitBreaker, service::process);

        for (int i = 0; i < 10; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>> Processing  " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            try {
                int result = decorated.apply(i);
                //print state of circuitBreaker
                System.out.println("Circuit Breaker State: " + circuitBreaker.getState());
                System.out.println("Result : " + result);
            } catch (Exception ignore) {
//                ignore.printStackTrace();
                //print state of circuit breaker
                System.out.println("Circuit Breaker State<Exception>: " + circuitBreaker.getState());
            }
        }
    }
}

