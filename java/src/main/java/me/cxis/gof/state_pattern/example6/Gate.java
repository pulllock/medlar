package me.cxis.gof.state_pattern.example6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Gate {

    private static List<GateStateConfig> stateConfigs = new ArrayList<>();

    static {
        stateConfigs.add(GateStateConfig
                .builder()
                .from(State.LOCKED)
                .to(State.UNLOCKED)
                .action(Action.INSERT_COIN)
                .handle(new OpenHandler())
                .build()
        );

        stateConfigs.add(GateStateConfig
                .builder()
                .from(State.LOCKED)
                .to(State.UNLOCKED)
                .action(Action.PASS)
                .handle(new AlarmHandler())
                .build()
        );

        stateConfigs.add(GateStateConfig
                .builder()
                .from(State.UNLOCKED)
                .to(State.LOCKED)
                .action(Action.INSERT_COIN)
                .handle(new ReturnCoinHandler())
                .build()
        );

        stateConfigs.add(GateStateConfig
                .builder()
                .from(State.UNLOCKED)
                .to(State.LOCKED)
                .action(Action.PASS)
                .handle(new CloseHandler())
                .build()
        );
    }

    private State state;

    public Gate(State state) {
        this.state = state;
    }

    public String execute(Action action) {
        Optional<GateStateConfig> optional = stateConfigs
                .stream()
                .filter(c -> c.getAction().equals(action) && state == c.getCurrentState())
                .findFirst();

        if (!optional.isPresent()) {
            return null;
        }

        GateStateConfig config = optional.get();
        setState(config.getNextState());
        return config.getHandler().execute();
    }

    public void setState(State state) {
        this.state = state;
    }
}
