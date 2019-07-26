package com.merricklabs

import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.mocks.MockWyattS3Client
import org.koin.dsl.module

val WyattTestModule = module {
    single(override = true) { MockWyattS3Client() as WyattS3Client }
}