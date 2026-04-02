// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.conveySubSystem;
import frc.robot.subsystems.Shooter.turretShootSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import static frc.robot.Constants.ShooterConstants.*;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  public static Command distrace (Drive drive) {
    return Commands.sequence(
       // Step 1: Drive backward
            Commands.run(() -> drive.arcadeDrive(0.7, 0.0), drive)
                .withTimeout(1.5),

            // Step 2: Stop driving
            Commands.runOnce(() -> drive.arcadeDrive(0.0, 0.0), drive)

            
    );
  }




  public static Command driveBackandShoot (Drive drive, turretShootSub shooter, conveySubSystem conveyor) {
    return Commands.sequence(
       // Step 1: Drive backward
            Commands.run(() -> drive.arcadeDrive(-0.5, 0.0), drive)
                .withTimeout(0.5),

            // Step 2: Stop driving
            Commands.runOnce(() -> drive.arcadeDrive(0.0, 0.0), drive),

            // Step 3: Spin up flywheel
            Commands.run(() -> shooter.prep(0.8), shooter)
                .withTimeout(0.5),

            // Step 4: Feed once flywheel is at speed
            Commands.run(() -> {
                shooter.prep(0.8);
                if (shooter.getSpeed() >= 0.8 * 0.75) {
                    conveyor.setSpeed(0.35);
                    shooter.startTunnel(0.4);
                }
            }, shooter, conveyor).withTimeout(3),

            // Step 5: Stop everything
            Commands.runOnce(() -> {
                shooter.stopShoot();
                conveyor.stop();
            }, shooter, conveyor)


    );
  }




  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
