package jp.ac.ccmc.festival_entry.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jp.ac.ccmc.festival_entry.model.Performer;
import jp.ac.ccmc.festival_entry.repository.PerformerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final PerformerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            return;
        }
        save("佐藤 葵", "090-1111-2222", "和太鼓", 20, true);
        save("田中 蓮", "080-3333-4444", "オリジナル曲", 30, false);
        save("陳 美玲", "070-5555-6666", "茶席体験", 45, true);
    }

    private void save(String name, String phone, String title, int minutes, boolean needHelp) {
        Performer performer = new Performer();
        performer.setName(name);
        performer.setPhone(phone);
        performer.setTitle(title);
        performer.setMinutes(minutes);
        performer.setNeedHelp(needHelp);
        repository.save(performer);
    }
}
