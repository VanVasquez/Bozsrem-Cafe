import java.util.List;

public class Reports {
    private List<Item> report;
    public void Save_Report (Item item){
        report.add(item);
    }
}

class Sales extends Reports{
}

class Stocks extends Reports {

}