package io.openems.edge.ess.mr.gridcon;

import java.util.Arrays;
import java.util.stream.Stream;

import io.openems.edge.common.channel.AbstractReadChannel;
import io.openems.edge.common.channel.BooleanReadChannel;
import io.openems.edge.common.channel.BooleanWriteChannel;
import io.openems.edge.common.channel.FloatReadChannel;
import io.openems.edge.common.channel.FloatWriteChannel;
import io.openems.edge.common.channel.IntegerReadChannel;
import io.openems.edge.common.channel.IntegerWriteChannel;
import io.openems.edge.common.channel.StateCollectorChannel;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.ess.api.ManagedSymmetricEss;
import io.openems.edge.ess.api.SymmetricEss;

public class Utils {
	public static Stream<? extends AbstractReadChannel<?>> initializeChannels(GridconPCS ess) {
		// Define the channels. Using streams + switch enables Eclipse IDE to tell us if
		// we are missing an Enum value.
		return Stream.of( //
				Arrays.stream(OpenemsComponent.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case STATE:
						return new StateCollectorChannel(ess, channelId);
					}
					return null;
				}), Arrays.stream(SymmetricEss.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case SOC:					
					case ACTIVE_CHARGE_ENERGY:						
					case ACTIVE_DISCHARGE_ENERGY:
						return new IntegerReadChannel(ess, channelId);
					case MAX_ACTIVE_POWER:
						return new IntegerReadChannel(ess, channelId, GridconPCS.MAX_APPARENT_POWER);
					case GRID_MODE:
						return new IntegerReadChannel(ess, channelId, SymmetricEss.GridMode.UNDEFINED.ordinal());
					case ACTIVE_POWER:
					case REACTIVE_POWER:
						return new IntegerReadChannel(ess, channelId);
						//return new FloatReadChannel(ess, channelId); // causes java.lang.IllegalArgumentException: [null/ActivePower]: Types do not match. Got [FLOAT]. Expected [INTEGER].
					}
					return null;
				}), Arrays.stream(ManagedSymmetricEss.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case DEBUG_SET_ACTIVE_POWER:
					case DEBUG_SET_REACTIVE_POWER:
						return new IntegerReadChannel(ess, channelId);
					}
					return null;
				}), Arrays.stream(GridconPCS.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					
					case SYSTEM_CURRENT_STATE:
					case SYSTEM_CURRENT_PARAMETER_SET:
					case SYSTEM_NUMBER_ERROR_WARNINGS:
					case SYSTEM_UTILIZATION:
					case SYSTEM_ERROR_CODE:					
					case CCU_ERROR_CODE:
						return new IntegerReadChannel(ess, channelId);

					case SYSTEM_SERVICE_MODE:
					case SYSTEM_REMOTE_MODE:
					case SYSTEM_MEASUREMENTS_LIFEBIT:
					case SYSTEM_CCU_LIFEBIT:
					case PCS_CCU_STATE_DERATING_HARMONICS:
					case PCS_CCU_STATE_DERATING_POWER:
					case PCS_CCU_STATE_ERROR:
					case PCS_CCU_STATE_IDLE:
					case PCS_CCU_STATE_OVERLOAD:
					case PCS_CCU_STATE_PAUSE:
					case PCS_CCU_STATE_PRE_CHARGE:
					case PCS_CCU_STATE_READY:
					case PCS_CCU_STATE_RUN:
					case PCS_CCU_STATE_SHORT_CIRCUIT_DETECTED:
					case PCS_CCU_STATE_SIA_ACTIVE:
					case PCS_CCU_STATE_STOP_PRE_CHARGE:
					case PCS_CCU_STATE_VOLTAGE_RAMPING_UP:

						return new BooleanReadChannel(ess, channelId);
					case ACF_FREQUENCY:
					case ACF_RELATIVE_THD_FACTOR:
					case ACF_VOLTAGE_RMS_L12:
					case ACF_VOLTAGE_RMS_L23:
					case ACF_VOLTAGE_RMS_L31:
					case ACF_CURRENT_RMS_L1:
					case ACF_CURRENT_RMS_L2:
					case ACF_CURRENT_RMS_L3:
					case ACF_ABSOLUTE_THD_FACTOR:
					case ACF_DISTORSION_POWER:
					case CCU_CURRENT_IL1:
					case CCU_CURRENT_IL2:
					case CCU_CURRENT_IL3:
					case CCU_FREQUENCY:
					case CCU_POWER_P:
					case CCU_POWER_Q:
					case CCU_VOLTAGE_U12:
					case CCU_VOLTAGE_U23:
					case CCU_VOLTAGE_U31:
						return new FloatReadChannel(ess, channelId);
					
					case SYSTEM_COMMAND:					
					case SYSTEM_PARAMETER_SET:
					case SYSTEM_ERROR_ACKNOWLEDGE:
					case PCS_COMMAND_ERROR_CODE_FALLBACK:
					case PCS_COMMAND_TIME_SYNC_DATE:
					case PCS_COMMAND_TIME_SYNC_TIME:
					case PCS_COMMAND_CONTROL_WORD:
					case PCS_CONTROL_PARAMETER_P_CONTROL_MODE:
						return new IntegerWriteChannel(ess, channelId);
					
					case SYSTEM_FIELDBUS_DEVICE_LIFEBIT:
					case PCS_COMMAND_CONTROL_WORD_ACKNOWLEDGE:
					case PCS_COMMAND_CONTROL_WORD_ACTIVATE_HARMONIC_COMPENSATION:
					case PCS_COMMAND_CONTROL_WORD_ACTIVATE_SHORT_CIRCUIT_HANDLING:
					case PCS_COMMAND_CONTROL_WORD_BLACKSTART_APPROVAL:
					case PCS_COMMAND_CONTROL_WORD_ENABLE_IPU_1:
					case PCS_COMMAND_CONTROL_WORD_ENABLE_IPU_2:
					case PCS_COMMAND_CONTROL_WORD_ENABLE_IPU_3:
					case PCS_COMMAND_CONTROL_WORD_ENABLE_IPU_4:
					case PCS_COMMAND_CONTROL_WORD_ID_1_SD_CARD_PARAMETER_SET:
					case PCS_COMMAND_CONTROL_WORD_ID_2_SD_CARD_PARAMETER_SET:
					case PCS_COMMAND_CONTROL_WORD_ID_3_SD_CARD_PARAMETER_SET:
					case PCS_COMMAND_CONTROL_WORD_ID_4_SD_CARD_PARAMETER_SET:
					case PCS_COMMAND_CONTROL_WORD_MODE_SELECTION:
					case PCS_COMMAND_CONTROL_WORD_PLAY:
					case PCS_COMMAND_CONTROL_WORD_READY:
					case PCS_COMMAND_CONTROL_WORD_STOP:
					case PCS_COMMAND_CONTROL_WORD_SYNC_APPROVAL:
					case PCS_COMMAND_CONTROL_WORD_TRIGGER_SIA:
						return new BooleanWriteChannel(ess, channelId);
						

					case PCS_COMMAND_CONTROL_PARAMETER_F0:
					case PCS_COMMAND_CONTROL_PARAMETER_P_REF:
					case PCS_COMMAND_CONTROL_PARAMETER_Q_REF:
					case PCS_COMMAND_CONTROL_PARAMETER_U0:
						
					case PCS_CONTROL_PARAMETER_F_P_DROOP_T1_MAIN:
					case PCS_CONTROL_PARAMETER_F_P_DRROP_MAIN:
					case PCS_CONTROL_PARAMETER_P_CONTROL_LIM_ONE:
					case PCS_CONTROL_PARAMETER_P_CONTROL_LIM_TWO:
					
					case PCS_CONTROL_PARAMETER_P_F_DEAD_BAND:
					case PCS_CONTROL_PARAMETER_P_F_DROOP_MAIN:
					case PCS_CONTROL_PARAMETER_P_U_DEAD_BAND:
					case PCS_CONTROL_PARAMETER_P_U_DROOP:
					case PCS_CONTROL_PARAMETER_P_U_MAX_CHARGE:
					case PCS_CONTROL_PARAMETER_P_U_MAX_DISCHARGE:
					case PCS_CONTROL_PARAMETER_Q_LIMIT:
					case PCS_CONTROL_PARAMETER_Q_U_DEAD_BAND:
					case PCS_CONTROL_PARAMETER_Q_U_DROOP_MAIN:
					case PCS_CONTROL_PARAMETER_U_Q_DROOP_MAIN:
					case PCS_CONTROL_PARAMETER_U_Q_DROOP_T1_MAIN:
						
					case PCS_CONTROL_IPU_1_PARAMETERS_DC_VOLTAGE_SETPOINT:
					case PCS_CONTROL_IPU_1_PARAMETERS_DC_CURRENT_SETPOINT:
					case PCS_CONTROL_IPU_1_PARAMETERS_U0_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_1_PARAMETERS_F0_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_1_PARAMETERS_Q_REF_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_1_PARAMETERS_P_REF_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_1_PARAMETERS_P_MAX_DISCHARGE:
					case PCS_CONTROL_IPU_1_PARAMETERS_P_MAX_CHARGE:
					
					case PCS_CONTROL_IPU_2_PARAMETERS_DC_VOLTAGE_SETPOINT:
					case PCS_CONTROL_IPU_2_PARAMETERS_DC_CURRENT_SETPOINT:
					case PCS_CONTROL_IPU_2_PARAMETERS_U0_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_2_PARAMETERS_F0_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_2_PARAMETERS_Q_REF_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_2_PARAMETERS_P_REF_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_2_PARAMETERS_P_MAX_DISCHARGE:
					case PCS_CONTROL_IPU_2_PARAMETERS_P_MAX_CHARGE:
						
					case PCS_CONTROL_IPU_3_PARAMETERS_DC_VOLTAGE_SETPOINT:
					case PCS_CONTROL_IPU_3_PARAMETERS_DC_CURRENT_SETPOINT:
					case PCS_CONTROL_IPU_3_PARAMETERS_U0_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_3_PARAMETERS_F0_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_3_PARAMETERS_Q_REF_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_3_PARAMETERS_P_REF_OFFSET_TO_CCU_VALUE:
					case PCS_CONTROL_IPU_3_PARAMETERS_P_MAX_DISCHARGE:
					case PCS_CONTROL_IPU_3_PARAMETERS_P_MAX_CHARGE:
					
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_DC_VOLTAGE_SETPOINT:
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_WEIGHT_STRING_A:
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_WEIGHT_STRING_B:
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_WEIGHT_STRING_C:
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_I_REF_STRING_A:
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_I_REF_STRING_B:
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_I_REF_STRING_C:	
					case PCS_CONTROL_IPU_4_DC_DC_CONVERTER_PARAMETERS_DC_DC_STRING_CONTROL_MODE:
						return new FloatWriteChannel(ess, channelId); // TODO!!
					
					}
					return null;
				}) //
		).flatMap(channel -> channel);
	}
}
