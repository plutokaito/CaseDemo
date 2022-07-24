package thinking.in.springboot.samples.spring50.bean;


import thinking.in.springboot.samples.spring50.annotations.TransactionService;

@TransactionService(name = "transactionalServiceBean")
public class TransactionalServiceBean {

    public void save() {
        System.out.println("保存操作...");
    }
}
