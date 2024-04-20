package dev.toastersrpg.pdc

import dev.toastersrpg.race.lib.RaceInfo
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RaceInfoAdapter : PersistentDataType<ByteArray, RaceInfo> {
    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<RaceInfo> {
        return RaceInfo::class.java
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): RaceInfo {
        ByteArrayInputStream(primitive).use { byteArrayOutputStream ->
            ObjectInputStream(byteArrayOutputStream).use { objectInputStream: ObjectInputStream ->
                return objectInputStream.readObject() as RaceInfo
            }
        }
    }

    override fun toPrimitive(complex: RaceInfo, context: PersistentDataAdapterContext): ByteArray {
        ByteArrayOutputStream().use { byteArrayOutputStream: ByteArrayOutputStream ->
            ObjectOutputStream(byteArrayOutputStream).use { objectOutputStream: ObjectOutputStream ->
                objectOutputStream.writeObject(complex)
            }
            return byteArrayOutputStream.toByteArray()
        }
    }
}