package demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(method = RequestMethod.POST)
@Retention(RetentionPolicy.RUNTIME)
public @interface Post {

	@AliasFor(annotation = RequestMapping.class, attribute = "path")
	String[] value() default {};

}