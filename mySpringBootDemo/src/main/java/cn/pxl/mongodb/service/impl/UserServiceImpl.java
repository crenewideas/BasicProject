//package cn.pxl.mongodb.service.impl;
//
//import cn.pxl.mongodb.pojo.User;
//import cn.pxl.mongodb.service.intf.UserRepository;
//import cn.pxl.mongodb.service.intf.UserService;
//import com.mongodb.client.result.DeleteResult;
//import com.mongodb.client.result.UpdateResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.*;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    //自动配置类会向spring容器中注入实现类。
//    @Autowired
//    private MongoTemplate mongoTemplate = null;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void saveUser(User user) {
//        mongoTemplate.save(user);
//    }
//
//    @Override
//    public DeleteResult deleteUser(long id) {
//        Criteria whereExample = Criteria.where("id").is(id);
//        Query query = Query.query(whereExample);
//        return mongoTemplate.remove(query);
//    }
//
//    @Override
//    public List<User> findByName(String name) {
//        Criteria whereExample = Criteria.where("userName").is(name);
//        Query query = Query.query(whereExample);
//        return mongoTemplate.find(query,User.class);
//    }
//
//    @Override
//    public UpdateResult updateUser(User user) {
//        Criteria criteria = Criteria.where("id").is(user.getId());
//        Query query = Query.query(criteria);
//        return mongoTemplate.updateFirst(query,new Update(),User.class);
//    }
//
//    @Override
//    public User getUser(long id) {
//        User mongoDbUser = mongoTemplate.findById(id, User.class);
//        Optional<User> repositoryUserOptional = userRepository.findById(id);
//        User repositoryUser;
//        repositoryUser = repositoryUserOptional.orElse(null);
//        System.out.println(mongoDbUser == repositoryUser);
//        return repositoryUser;
//    }
//}
