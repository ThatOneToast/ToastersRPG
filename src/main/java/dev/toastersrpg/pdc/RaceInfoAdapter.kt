package dev.toastersrpg.pdc

import dev.toastersrpg.races.RaceInfo
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

    override fun fromPrimitive(bytes: ByteArray, context: PersistentDataAdapterContext): RaceInfo {
        ByteArrayInputStream(bytes).use { byteArrayInputStream ->
            ObjectInputStream(byteArrayInputStream).use { objectInputStream ->
                return objectInputStream.readObject() as RaceInfo
            }
        }
    }

    override fun toPrimitive(handler: RaceInfo, context: PersistentDataAdapterContext): ByteArray {
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            ObjectOutputStream(byteArrayOutputStream).use { objectOutputStream ->
                objectOutputStream.writeObject(handler)
                return byteArrayOutputStream.toByteArray()
            }
        }
    }

}