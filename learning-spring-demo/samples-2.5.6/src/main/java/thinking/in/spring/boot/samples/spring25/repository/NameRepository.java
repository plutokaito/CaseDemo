package thinking.in.spring.boot.samples.spring25.repository;

import thinking.in.spring.boot.samples.spring25.annotation.StringRepository;

import java.util.Arrays;
import java.util.List;

@StringRepository("chineseNameRepository")
public class NameRepository {

    public List<String> findAll() {
        return Arrays.asList("张三", "李四", "小马哥");
    }
}
