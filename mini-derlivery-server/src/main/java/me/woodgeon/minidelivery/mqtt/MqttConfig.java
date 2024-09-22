package me.woodgeon.minidelivery.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
@EnableIntegration
@Configuration
public class MqttConfig {

    private static final String MQTT_BROKER_URL = "tcp://172.30.1.64:1883";  // 브로커의 URL과 포트
    private static final String MQTT_TOPIC = "json";  // 메시지를 전달할 주제

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{MQTT_BROKER_URL});
        options.setCleanSession(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("order-broker", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(MQTT_TOPIC);
        return messageHandler;
    }

    @Bean
    public IntegrationFlow mqttOutboundFlow() {
        return IntegrationFlows.from(mqttOutboundChannel())
                .handle(mqttOutbound())
                .get();
    }

    @Bean
    public MqttGateway mqttGateway() {
        return new MqttGateway() {
            @Override
            public void sendToMqtt(String data) {
                // 여기에서 실제 MQTT 핸들러를 통해 메시지를 보냅니다
                System.out.println("Sending data to MQTT: " + data);

                // MessageBuilder를 사용하여 String 데이터를 Message로 변환
                Message<String> message = MessageBuilder.withPayload(data).build();

                // 변환된 Message 객체를 mqttOutbound에 전달
                mqttOutbound().handleMessage(message);
            }
        };
    }
}
