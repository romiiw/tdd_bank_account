package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ModuleController {

    private Map<Integer, Module> modules = new HashMap<Integer, Module>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("Init Data");
    }

    @GetMapping("/module/test")
    public String test() {
        return "Module app is up and running!";
    }

    @GetMapping("/services/module/ping")
    public String ping() {
        String languageCode = "de";
        return "{ \"status\": \"ok\", \"userId\": \"admin"+ \"\", \"languageCode\": \"" + languageCode + "\",\"version\": \"0.0.2" + "\"}";
    }

    @GetMapping("/module/count")
    public int count() {
        return this.modules.size();
    }

    @GetMapping("/services/module")
    public List<PathListEntry<Integer>> module() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var module : this.modules.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(module.getId(), "moduleKey");
            entry.setName(module.getTitle());
            entry.getDetails().add(module.getDescription());
            entry.setTooltip(module.getDescription());
            result.add(entry);
        }
        return result.stream().sorted(Comparator.comparing(PathListEntry::getName)).toList();
    }

    @GetMapping("/services/module/{key}")
    public Module getModule(@PathVariable("key") Integer key) {
        return this.modules.get(key);
    }

    @PostMapping("/services/module")
    public void createModule(@RequestBody Module module) {
        var newId = this.modules.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        module.setId(newId);
        this.modules.put(newId, module);
    }

    @PutMapping("/services/module/{key}")
    public void updateModule(@PathVariable("key") Integer key, @RequestBody Module module) {
        module.setId(key);
        this.modules.put(key, module);
    }

    @DeleteMapping("/services/module/{key}")
    public Module deleteModule(@PathVariable("key") Integer key) {
        return this.modules.remove(key);
    }


}
