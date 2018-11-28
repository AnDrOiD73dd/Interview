import java.util.List;

public class ScreenReport implements IReport {
    @Override
    public void output(List<ReportItem> items) {
        System.out.println("Show info on screen");
        for (ReportItem item : items) {
            System.out.format("printer %s - %f \n\r", item.getDescription(), item.getAmount());
        }
    }
}
