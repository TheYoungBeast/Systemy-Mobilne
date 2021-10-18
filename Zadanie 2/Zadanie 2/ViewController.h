//
//  ViewController.h
//  Zadanie 2
//
//  Created by Dominik on 17/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SecondViewController.h"

@interface ViewController : UIViewController <SecondViewControllerDelegate>

@property (weak, nonatomic) IBOutlet UIButton *Button;
@property (weak, nonatomic) IBOutlet UILabel *messageLabel;
@property (weak, nonatomic) IBOutlet UITextField *inputField;
@property (weak, nonatomic) IBOutlet UITextField *surnameField;

- (IBAction)enter;

@end

