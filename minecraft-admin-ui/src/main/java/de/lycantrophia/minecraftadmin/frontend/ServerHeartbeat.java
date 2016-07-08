package de.lycantrophia.minecraftadmin.frontend;

import de.lycantrophia.minecraftadmin.frontend.design.ServerHeartbeatDesign;

class ServerHeartbeat extends ServerHeartbeatDesign {

    private static final int MAX_COLOR = 220;

    ServerHeartbeat() {
        setHeight(100, Unit.PERCENTAGE);
        setWidth(100, Unit.PERCENTAGE);
        setSizeFull();

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
}
