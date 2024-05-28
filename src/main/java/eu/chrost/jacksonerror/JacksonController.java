package eu.chrost.jacksonerror;

import eu.chrost.jacksonerror.off.ExampleObjectOff;
import eu.chrost.jacksonerror.on.ExampleObjectOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
class JacksonController {
    @GetMapping("/on/list")
    public List<ExampleObjectOn> onList() {
        return List.of(new ExampleObjectOn(1));
    }

    @GetMapping("/on")
    public ExampleObjectOn on() {
        return new ExampleObjectOn(1);
    }

    @GetMapping("/off/list")
    public List<ExampleObjectOff> offList() {
        return List.of(new ExampleObjectOff(1));
    }

    @GetMapping("/off")
    public ExampleObjectOff off() {
        return new ExampleObjectOff(1);
    }
}
