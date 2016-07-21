import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;

public class JMSTest implements MessageListener {
	public JMSTest() throws Exception {
		// create consumer and listen queue
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		////////////// 注意这里JMSAppender只支持TopicDestination，下面会说到////////////////
		Destination topicDestination = session.createTopic("logTopic");
		MessageConsumer consumer = session.createConsumer(topicDestination);
		consumer.setMessageListener(this);

		// log a message
		Logger logger = LoggerFactory.getLogger(JMSTest.class);
		logger.info("Info Log.");
		logger.warn("Warn Log");
		logger.error("Error Log.");

		// clean up
		Thread.sleep(1000);
		consumer.close();
		session.close();
		connection.close();
		System.exit(1);
	}

	public static void main(String[] args) throws Exception {
		new JMSTest();
	}

	public void onMessage(Message message) {
		try {
			// receive log event in your consumer
			LoggingEvent event = (LoggingEvent) ((ActiveMQObjectMessage) message).getObject();
			System.out.println("Received log [" + event.getLevel() + "]: " + event.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
