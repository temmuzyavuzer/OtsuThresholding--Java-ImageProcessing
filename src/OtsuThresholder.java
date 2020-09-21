public class OtsuThresholder
{
    private int histData[];
    private int maxLevel;
    private int threshold;

    public OtsuThresholder()
    { histData = new int[256]; }
    public int doThreshold(byte[] Data, byte[] monoData)
    {
        int ptr; ptr = 0;
        while (ptr < histData.length)
            histData[ptr++] = 0;
        ptr = 0;maxLevel = 0;
        while (ptr < Data.length) {
            int h = 0xFF & Data[ptr];histData[h] ++;
            if (histData[h] > maxLevel) maxLevel = histData[h];ptr ++; }

        int total = Data.length;float sum = 0;
        for (int t=0 ; t<256 ; t++) sum += t * histData[t];

        float sumB = 0;int wB = 0;int wF = 0;
        float varMax = 0;threshold = 0;

        for (int t=0 ; t<256 ; t++)
        { wB += histData[t];
            if (wB == 0) continue;
            wF = total - wB;
            if (wF == 0) break;
            sumB += (float) (t * histData[t]);
            float mB = sumB / wB;float mF = (sum - sumB) / wF;
            float varBetween = (float)wB * (float)wF * (mB - mF) * (mB - mF);

            if (varBetween > varMax) {
                varMax = varBetween;threshold = t; } }

        if (monoData != null)
        { ptr = 0;
            while (ptr < Data.length)
            { monoData[ptr] = ((0xFF & Data[ptr]) >= threshold) ? (byte) 255 : 0;ptr ++; }
        }return threshold; }}
