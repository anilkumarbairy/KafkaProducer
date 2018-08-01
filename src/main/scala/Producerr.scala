import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import scala.util.control.Exception
import java.util.{Date, Properties}
import scala.util.Random
object Producerr {
  def main(args: Array[String]): Unit = {
    val events = args(0).toInt
    val topic = args(1)
     val rnd = new Random()
    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("key.serializer",classOf[StringSerializer])
    properties.put("value.serializer",classOf[StringSerializer])
    properties.put("partitioner.class","CustomPart")
    val producer = new KafkaProducer[String,String](properties)
    for (nEvents <- Range(0, events)) {
    val runtime = new Date().getTime()
    val ip = "192.168.2." + rnd.nextInt(255)
    val msg = runtime + "," + nEvents + ",www.example.com," + ip
    val data = new ProducerRecord[String, String](topic, ip, msg)
    try{
      producer.send(data)
    }
    catch{
      case e:Exception => println(e)
    }
    println(msg)
    }
     producer.close()
  }
}