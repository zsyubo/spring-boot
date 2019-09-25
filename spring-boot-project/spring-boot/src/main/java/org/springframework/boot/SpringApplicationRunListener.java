/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * Spring 运行的监听器，在spring不同的声明周期阶段被调用
 * Listener for the {@link SpringApplication} {@code run} method.
 * {@link SpringApplicationRunListener}s are loaded via the {@link SpringFactoriesLoader}
 * and should declare a public constructor that accepts a {@link SpringApplication}
 * instance and a {@code String[]} of arguments. A new
 * {@link SpringApplicationRunListener} instance will be created for each run.
 *
 * @author Phillip Webb
 * @author Dave Syer
 * @author Andy Wilkinson
 */
public interface SpringApplicationRunListener {

	/**
	 * 当第一次运行run方法时调用立即调用，能用于非常早期的初始化
	 * Called immediately when the run method has first started. Can be used for very
	 * early initialization.
	 */
	void starting();

	/**
	 * 当环境准备好了 第一次调用。但是是在ApplicationContext创建之前。
	 * Called once the environment has been prepared, but before the
	 * {@link ApplicationContext} has been created.
	 * @param environment the environment
	 */
	void environmentPrepared(ConfigurableEnvironment environment);

	/**
	 * 在ApplicationContext 创建和准备好之后调用，但是在 资源加载之前。
	 * Called once the {@link ApplicationContext} has been created and prepared, but
	 * before sources have been loaded.
	 * @param context the application context
	 */
	void contextPrepared(ConfigurableApplicationContext context);

	/**
	 * 在应用上下文 被加载时调用，但是在刷新之前。
	 * Called once the application context has been loaded but before it has been
	 * refreshed.
	 * @param context the application context
	 */
	void contextLoaded(ConfigurableApplicationContext context);

	/**
	 * 在上下文 刷新时， CommandLineRunner、ApplicationRunner并没有被调用之前。
	 * 	 * The context has been refreshed and the application has started but
	 * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
	 * ApplicationRunners} have not been called.
	 * @param context the application context.
	 * @since 2.0.0
	 */
	void started(ConfigurableApplicationContext context);

	/**
	 * 在run方法完成之前调用，也就是CommandLineRunner、ApplicationRunner已经被调用后调用
	 * Called immediately before the run method finishes, when the application context has
	 * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
	 * {@link ApplicationRunner ApplicationRunners} have been called.
	 * @param context the application context.
	 * @since 2.0.0
	 */
	void running(ConfigurableApplicationContext context);

	/**
	 * 当运行应用失败的时候。
	 * Called when a failure occurs when running the application.
	 * @param context the application context or {@code null} if a failure occurred before
	 * the context was created
	 * @param exception the failure
	 * @since 2.0.0
	 */
	void failed(ConfigurableApplicationContext context, Throwable exception);

}
