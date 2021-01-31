package com.example.hotwirespringbootdemochat.exceptions

import com.example.hotwirespringbootdemochat.models.ApplicationRecord
import kotlin.reflect.KClass

class RecordNotFound(klass: KClass<out ApplicationRecord<out Any?>>, id: Any) : RuntimeException("Couldn't find ${klass.simpleName} with 'id'=${id}")