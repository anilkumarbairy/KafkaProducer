import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.PartitionInfo
import org.apache.kafka.common.record.InvalidRecordException

class CustomPart extends Partitioner{
   def configure(x$1: java.util.Map[String, _]): Unit = {
   }
   
   def partition(topic:String,key:Any,keyBytes:Array[Byte],value:Any,valueBytes:Array[Byte],cluster:Cluster) : Int = {
    val partitions:scala.collection.immutable.List[PartitionInfo] = cluster.partitionsForTopic(topic).asScala.toList
    val numPartitions:Int = partitions.size;
    for(partition <- partitions){
      println("Parition: "+ partition.partition())
      partition.inSyncReplicas().foreach(p => println("In Sync Replicas:"+ p))
      println("Leader: "+ partition.leader())
      println("Topic: "+ partition.topic())
      partition.replicas().foreach(r => println("Replicas: "+ r))
      println("----------------------------------------------")
    }
    if ((keyBytes == null) || (!(key.isInstanceOf[String])))
      throw new InvalidRecordException("We expect all messages to have customer name as key")
   val regex = "^\\d+.\\d+.\\d+.(\\d+)".r
   val regex(ip_address) = key.asInstanceOf[String]    
    if(ip_address.toInt>=0 && ip_address.toInt<100)
      1
    else if (ip_address.toInt>=100 && ip_address.toInt<200)
      2
    else
      3
   }
   def close():Unit = {}
}