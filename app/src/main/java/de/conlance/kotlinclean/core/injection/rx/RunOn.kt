package de.conlance.kotlinclean.core.injection.rx

import javax.inject.Qualifier


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class RunOn(val value: SchedulerType = SchedulerType.IO)