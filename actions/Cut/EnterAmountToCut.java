package scripts.actions.Cut;

import java.util.Random;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;

import scripts.Globals;
import scripts.sbf.action.Action;

public class EnterAmountToCut extends Action {
	Random randomizeEnterAmount = new Random();

	@Override
	public void execute() {
		Keyboard.typeSend(String.valueOf(randomizeEnterAmount.nextInt(90 - 27) + 27));
		Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				General.sleep(50, 70);
				return Player.getAnimation() != -1;
			}

		}, General.random(3000, 4000));
	}

	@Override
	public boolean isValid() {
		return Globals.ENTER_AMOUNT_TO_CUT.getStatus();
	}

}
