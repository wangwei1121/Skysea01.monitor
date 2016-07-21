
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jms-2.xml")
public class QueueSender {

	@Resource
	private JmsTemplate jmsTemplate;

	@Test
	public void getAppList() {
		sendMqMessage(null, "spring activemq queue type message !");
	}

	public void sendMqMessage(Destination destination, final String message) {
		if (null == destination) {
			destination = jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		System.out.println("spring send message...");
	}
}
