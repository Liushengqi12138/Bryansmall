package lsq.Bryan.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;
import lsq.Bryan.utils.Permission;
import lsq.Bryan.utils.SecurityException;

public class ServiceFactory {

	private ServiceFactory() {}
	private static ServiceFactory instance = new ServiceFactory();
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	//BusinessServiceProxy.addCategory(Category c)    or   BusinessServiceProxy.addGood(Good b)
	public BusinessService createService(User user) {
		BusinessService service = new BusinessServiceImpl();
		
		return (BusinessService) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				//得到web层调用的方法名称，例如addCategory
				String methodName = method.getName();//addCategory
				
				//反射出真实对象上相对应的方法，检查真实对象的方法上有没有权限注解                                
				/*
                 * 真实对象相对应的方法和代理对象相对应的方法具有共同的行为，
                 * 即代理对象的方法有什么参数，真实对象相对应的方法上就有什么参数。
                 * 
                 * 也就是说你代理对象的方法（Method）是什么，我就得到真实对象上相对应的方法（Method）
                 */                                                          
				                                                             //method.getParameterTypes()：得到代理对象的参数类型，例如Category.class
				Method realMethod = service.getClass().getMethod(methodName, method.getParameterTypes());
				
				//看真实对象上相对应的方法上有没有@Permission注解
				Permission permission = realMethod.getAnnotation(Permission.class);
				if (permission == null) {//意味着该方法上没有加权限标签，即访问该方法不需要权限
					return method.invoke(service, args);
				}
				
				//意味着真实对象相对应的方法上有权限注解，则得到访问该方法需要的权限
				Privilege p = new Privilege(permission.value());//得到方法需要的权限
				
				//接着要检查用户是否有权限，但在检查之前，还要判断用户有没有登录。
				if (user == null) {
					//照理说应该抛一个用户没有登录的异常出去，但我们不想那么麻烦了
					throw new SecurityException("您没有登录!");
				}
				
				//检查用户是否有权限
				List<Privilege> list = service.getUserAllPrivilege(user);//得到用户拥有的所有权限
				if (list.contains(p)) {
					return method.invoke(service, args);
				}
				//意味着用户没有权限，就要抛一个异常出去（抛一个编译时异常出去，希望web层检查这个异常，给用户一个友好提示，说他没有权限）
				throw new SecurityException("您没有权限!");
			}
		});
	}
}
