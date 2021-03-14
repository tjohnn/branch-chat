package com.example.branchchat.data.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Serializer(forClass = Date::class)
object DateSerializer: KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        val string = String.format(Locale.ENGLISH, "%1\$tF %1\$tT", value)
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Date {
        var timeString = decoder.decodeString()
        timeString = timeString.replace("T", " ")
        timeString = timeString.replace("Z", "")
        val format = SimpleDateFormat("yyyy-MM-dd H:m:s.S", Locale.ENGLISH)
        return try {
            requireNotNull(format.parse(timeString))
        } catch (e: ParseException) {
            Date(0)
        }
    }
}