package in.rk.learn.web.java.rest.interceptor.in;

import java.util.List;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

@Component
public class PreInvokeInterceptor extends AbstractPhaseInterceptor<Message> {

	public PreInvokeInterceptor() {
		super(Phase.PRE_INVOKE);
		System.out.println(this.getClass().getName() + " created");
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("App Interceptor:" + this.getClass().getName());
		readMessageBody(message);

	}

	// Learn - This is just to show where the message object is finally kept before
	// passing on to the service.
	@SuppressWarnings("rawtypes")
	private void readMessageBody(Message message) {
		List contents = message.getContent(List.class);
		if (contents != null) {
			for (Object object : contents) {
				System.out.println(object.getClass().getCanonicalName());
				System.out.println("Object found is :" + object);
			}
		} else {
			System.out.println("List.class does not exist in REST msg");
		}

	}

}
