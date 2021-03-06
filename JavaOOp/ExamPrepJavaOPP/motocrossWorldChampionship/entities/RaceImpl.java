package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Rider> riders;
    private List<Rider> ridersByName;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.ridersByName = new ArrayList<>();
        this.riders=new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return this.riders;
    }

    @Override
    public void addRider(Rider rider) {
        if (rider==null){
            throw new NullPointerException(ExceptionMessages.RIDER_INVALID);
        }
        if (!rider.getCanParticipate()){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE,rider.getName()));
        }
        if (this.ridersByName.stream().anyMatch(e->e.getName().equals(rider.getName()))){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_ALREADY_ADDED,rider.getName(),this.getName()));
        }

        this.riders.add(rider);
        this.ridersByName.add(rider);

    }

    private void setName(String name) {
        if (name==null||name.trim().isEmpty()||name.length()<5){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME,name,5));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps<1){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS,1));
        }

        this.laps = laps;
    }
}
