package org.example.dao.user

import java.util.UUID

data class UserEntity(var id: String = UUID.randomUUID().toString(), var name: String)