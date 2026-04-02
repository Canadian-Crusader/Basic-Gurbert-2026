// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;


public class turretShootSub extends SubsystemBase {
  static SparkMax turretMotor = new SparkMax(shooterMotorChannel, brushless);
    SparkMax tunnelMotor = new SparkMax(tunnelMotorChannel, brushless);
  
    
    // can do manually but add the brake
    // tell to set the id to brake it
    
    /** Creates a new turretShootSub. */
  
  
    public turretShootSub() {
    turretMotor.setInverted(false); // change before final ================================ change before final
    }
  
    public void prep(double turret) {
      turretMotor.set(turret);
    }
    
    public static double getSpeed() {
      return turretMotor.get();
  }

  public void startTunnel(double tunnel) {
    tunnelMotor.set(tunnel);
  }

  public void stopShoot() {
    turretMotor.stopMotor();
    tunnelMotor.stopMotor();
  } // velocity control
    // 
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Turret Speed", turretMotor.getMotorTemperature());
    // This method will be called once per scheduler run
  }
}
