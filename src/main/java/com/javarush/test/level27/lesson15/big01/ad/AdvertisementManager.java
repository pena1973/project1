package com.javarush.test.level27.lesson15.big01.ad;
// http://stackoverflow.com/questions/20342386/printing-out-result-in-0-1-knapsack-recursive-brute-force/20342705#20342705
//http://stackoverflow.com/questions/20342386/printing-out-result-in-0-1-knapsack-recursive-brute-force/24906552#24906552

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.*;

public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()
    {
        // создаем список оптимальных видео для прокрутки
        List<Advertisement> allAd = new ArrayList<Advertisement>();
        List<Advertisement> ad = new ArrayList<>();

        for (int i = 0; i < storage.list().size(); i++)
        {
            if ((storage.list().get(i).getHits() > 0) && (storage.list().get(i).getDuration() <= timeSeconds))
            {
                allAd.add(storage.list().get(i));
            }
        }

        if (allAd.size() == 0) throw new NoVideoAvailableException();

        findVideos(timeSeconds, allAd, allAd.size(), ad);

        Collections.sort(ad, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                int result = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (result != 0)
                    return result;

                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });

        for (Advertisement advertisement : ad)
        {

            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            advertisement.revalidate();
        }

    }

    private long findVideos(int allTimeCooking, List<Advertisement> allAd, int length, List<Advertisement> ad)
    {
        if (length == 0 || allTimeCooking == 0)
            return 0;

        if (allAd.get(length - 1).getDuration() > allTimeCooking)
            return findVideos(allTimeCooking, allAd, length - 1, ad);
        else
        {
            final int preTookSize = ad.size();
            final long took = (allAd.get(length - 1).getAmountPerOneDisplaying() +
                    findVideos(allTimeCooking - allAd.get(length - 1).getDuration(), allAd, length - 1, ad));

            final int preLeftSize = ad.size();
            final long left = findVideos(allTimeCooking, allAd, length - 1, ad);

            if (took > left)
            {
                if (ad.size() > preLeftSize)
                    ad.subList(preLeftSize, ad.size()).clear();
                ad.add(allAd.get(length - 1));
                return took;
            } else
            {
                if (preLeftSize > preTookSize)
                    ad.subList(preTookSize, ad.size()).clear();
                return left;
            }
        }
    }
}
