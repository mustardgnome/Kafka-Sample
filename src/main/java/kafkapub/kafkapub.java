package kafkapub;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
public class kafkapub {
	public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("group.id", "test-group");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        try{
            for(int i = 0; i < 2; i++){
                System.out.println(i);
                kafkaProducer.send(new ProducerRecord<String, String>("qqq", Integer.toString(i), "test message - " + i)).get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        	kafkaProducer.flush();
            kafkaProducer.close();
        }
    }
}
