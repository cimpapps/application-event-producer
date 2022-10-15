package com.cimpapps.applicationeventproducer.greeter;

import com.cimpapps.applicationeventproducer.aspect.Produce;
import com.cimpapps.applicationeventproducer.revert.RevertContext;
import com.cimpapps.applicationeventproducer.revert.RevertIfFails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreeterController {

    private final RevertContext revertContext;

    @GetMapping("/hello/{name}")
    @RevertIfFails
    @Produce
    public String hello(@PathVariable String name) {
        String greet = getGreet(name);
        doSomething();
        revertContext.addRevertMethod(() -> System.out.println("revert something"));
        doAnother();
        revertContext.addRevertMethod(() -> System.out.println("revert another"));
        // Daci sare si intra in revert proxy
        thirdThingThrowsException();
        return greet;
    }

    private void thirdThingThrowsException() {
        throw new IllegalArgumentException();
    }

    private void doAnother() {
        System.out.println("another");
    }

    private void doSomething() {
        System.out.println("something");
    }

    private String getGreet(String name) {
        return String.format("hello %s", name);
    }
}
