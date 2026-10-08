// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import java.lang.reflect.Method;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class fuelsubsystem extends SubsystemBase {
  private final SparkMax intakerLanucherMotor;
  private final SparkMax feederMotor;
  private static final SparkMaxConfig intakeLaunchconfig=
   new SparkMaxConfig();
  private static final SparkMaxConfig feederConfig=
   new SparkMaxConfig();

  static {
    feederConfig.smartCurrentLimit(50).idleMode(IdleMode.kCoast);

    intakeLaunchconfig.smartCurrentLimit(50).idleMode(IdleMode.kCoast);
  }

  /** Creates a new fuelsubsystem. */
  public fuelsubsystem() {
    intakerLanucherMotor = new SparkMax(10, MotorType.kBrushless);
    feederMotor = new SparkMax(11, MotorType.kBrushed);

    intakerLanucherMotor.configure(intakeLaunchconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    feederMotor.configure(feederConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  private void setSpeedMotors (double feederspeed, double intakespeed){
    intakerLanucherMotor.set(intakespeed);
    feederMotor.set(feederspeed);
  }

  private void stopSpeedMotors () {
    intakerLanucherMotor.stopMotor();
    feederMotor.stopMotor();
  }

  public Command intakeCommand(){
    return this.runEnd(()-> setSpeedMotors(-1,1),()-> stopSpeedMotors());
  }

  public Command ejackCommand () {
    return this.runEnd(()-> setSpeedMotors(-1, 0.8), ()-> stopSpeedMotors());
  }

  public Command launchCommand (){
   return this.runEnd(()-> setSpeedMotors(-1, 0.6), ()-> stopSpeedMotors());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
