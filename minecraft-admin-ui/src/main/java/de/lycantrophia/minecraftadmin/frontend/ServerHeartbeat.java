package de.lycantrophia.minecraftadmin.frontend;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.addon.charts.themes.ValoDarkTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import de.lycantrophia.minecraftadmin.frontend.design.ServerHeartBeatDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServerHeartbeat extends ServerHeartBeatDesign {

    private static final int MAX_COLOR = 220;

    private static final String CPU_LOAD        = "CPU Load";
    private static final String RAM_USAGE       = "RAM Usage";
    private static final String USERS_ONLINE    = "Users Online";
    private static final int    UPDATE_INTERVAL = 2500;

    private        final DataSeries cpuLoadSeries     = new DataSeries();
    private        final DataSeries ramUsageSeries    = new DataSeries();
    private        final DataSeries usersOnlineSeries = new DataSeries();

    ServerHeartbeat() {
        setHeight(100, Unit.PERCENTAGE);
        setWidth(100, Unit.PERCENTAGE);
        setSizeFull();

        final Chart chart = getChart();
        chart.setWidth(100, Unit.PERCENTAGE);
        chart.setHeight(100, Unit.PERCENTAGE);
        layoutGraph.addComponent(chart);

        runWhileAttached(chart, () -> {
            final long time = System.currentTimeMillis();

            final boolean shift = cpuLoadSeries.getData().size() > 10;
            final boolean update = true;
            cpuLoadSeries.add(new DataSeriesItem(time, Math.random() * 100), update, shift);
            ramUsageSeries.add(new DataSeriesItem(time, Math.floor(Math.random() * 4096)), update, shift);
            usersOnlineSeries.add(new DataSeriesItem(time, Math.floor(Math.random() * 20)), update, shift);

        }, UPDATE_INTERVAL, UPDATE_INTERVAL);


        for(int i = 0; i <= 100; i+=20)
        {
            addLineToLog(createColor(i/100.0));
        }
    }

    private void addLineToLog(String line)
    {
        textAreaServerLog.setValue(textAreaServerLog.getValue() + '\n' + line);
    }

    private String createColor(double value)
    {
        int r = value < 0.5 ? (int)(value * 2 * MAX_COLOR) : MAX_COLOR;
        int g = value > 0.5 ? (int)((value - 1) * -2 * MAX_COLOR) : MAX_COLOR;

        return rgbToHexString(r, g, 0);
    }

    private String rgbToHexString(final int r, final int g, final int b)
    {
        return '#' + intToHexString(r) + intToHexString(g) + intToHexString(b);
    }

    private String intToHexString(int r) {
        String s = Integer.toHexString(r);
        return s.length() == 1 ? ('0' + s) : s;
    }

    private Chart getChart() {
        ChartOptions.get().setTheme(new ValoDarkTheme());

        Chart         chart  = new Chart();
        Configuration conf   = chart.getConfiguration();
        Color[]       colors = new Color[] { new SolidColor(255, 100, 100), new SolidColor(255, 150, 50), new SolidColor(255, 100, 255)};

        conf.getChart().setZoomType(ZoomType.XY);
        conf.setTitle("");


        final XAxis xAxis = conf.getxAxis();
        xAxis.setType(AxisType.DATETIME);
        xAxis.setTickPixelInterval(150);

        conf.addyAxis(createAxis(colors[0], CPU_LOAD, "return this.value +'%'", true, 100));
        conf.addyAxis(createAxis(colors[1], RAM_USAGE, "return this.value +'MB'", true, 4096));
        conf.addyAxis(createAxis(colors[2], USERS_ONLINE, "return this.value", true, 20));

        chart.drawChart(conf);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() { "
                             + "var unit = { 'CPU Load': '%', 'RAM Usage': 'MB', 'Users Online': '' }[this.series.name];"
                             + "return ''+ this.x +': '+ this.y +' '+ unit; }");
        conf.setTooltip(tooltip);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.HORIZONTAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setX(10);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setY(-10);
        legend.setFloating(true);
        conf.setLegend(legend);

        final long startTime = System.currentTimeMillis();
        initSeries(cpuLoadSeries, 0, CPU_LOAD, colors[0], startTime);
        initSeries(ramUsageSeries, 1, RAM_USAGE, colors[1], startTime);
        initSeries(usersOnlineSeries, 2, USERS_ONLINE, colors[2], startTime);

        conf.addSeries(cpuLoadSeries);
        conf.addSeries(ramUsageSeries);
        conf.addSeries(usersOnlineSeries);

        chart.drawChart(conf);

        return chart;
    }

    private void initSeries(final DataSeries dataSeries, final int yAxis, final String name, final Color color, final long startTime) {

        final List<DataSeriesItem> items = new ArrayList<>();
        items.add(new DataSeriesItem(startTime - 2500, 0));
        items.add(new DataSeriesItem(startTime, 0));

        dataSeries.setData(items);
        dataSeries.setName(name);
        dataSeries.setyAxis(yAxis);
        PointOptions plotOptions = new PlotOptionsLine();
        plotOptions.setColor(color);
        plotOptions.setMarker(new Marker(false));
        dataSeries.setPlotOptions(plotOptions);
    }

    private YAxis createAxis(final Color color, final String title, final String valueFormat, final boolean oppositeSide, final int max)
    {
        final Style style = new Style();
        style.setColor(color);

        final Labels labels = new Labels();
        labels.setStyle(style);
        labels.setFormatter(valueFormat);

        final YAxis yAxis = new YAxis();
        yAxis.setPlotLines(new PlotLine(0, 1, color));
        yAxis.setGridLineWidth(0);
        yAxis.setLabels(labels);
        yAxis.setOpposite(true);
        yAxis.setTitle(new AxisTitle(title));
        yAxis.setOpposite(oppositeSide);
        yAxis.setExtremes(0, max);

        return yAxis;
    }

    /**
     * Runs given task repeatedly until the reference component is attached
     *
     * @param component The component to monitor
     * @param task      The task to execute
     * @param interval  the refresh interval
     * @param initialPause A timeout after tas is started
     */
    private static void runWhileAttached(final Component component, final Runnable task, final int interval, final int initialPause) {
        // Until reliable push available in our demo servers
        UI.getCurrent().setPollInterval(interval);

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(initialPause);
                    int i = 0;
                    while (component.getUI() != null) {
                        Future<Void> future = component.getUI().access(task);
                        future.get();
                        Thread.sleep(interval);
                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "run #" + i++);
                    }
                }
                catch (InterruptedException | com.vaadin.ui.UIDetachedException ignore) { }

                catch (ExecutionException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Stopping repeating command due to an exception", e);
                }

                catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Unexpected exception while running scheduled update", e);
                }

                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Thread stopped");
            }
        };
        thread.start();
    }
}
