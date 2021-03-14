package com.example.branchchat.data.mapper

interface Mapper<FROM, TO> {
    fun map(input: FROM): TO
}