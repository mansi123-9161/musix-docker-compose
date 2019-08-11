//package com.stackroute.dataSeed;
//
//import com.stackroute.domain.Track;
//import com.stackroute.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//import org.springframework.context.ApplicationListener;
//
////It will get execute, before the server get starts
//@Component
////Property source annotation is used to externalize configuration to a property file.
//@PropertySource("classpath:application.properties")
//public class ApplicationListenerSeedData implements ApplicationListener {
//    //    Value annotation is used for injecting values into fields in Spring-managed beans
//    @Value("${track1.id}")
//    private int id;
//    @Value("${track1.name}")
//    private String name;
//    @Value("${track1.comment}")
//    private String comment;
//    //    object of track created
//    Track track1 = new Track();
//    private TrackRepository trackRepository;
//
//    //    Track repository constructor
//    @Autowired
//    public ApplicationListenerSeedData(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//
//    //    method over ridden
//    @Override
//    public void onApplicationEvent(ApplicationEvent event) {
//        track1.setId(id);
//        track1.setName(name);
//        track1.setComment(comment);
//        trackRepository.save(track1);
//    }
//}
