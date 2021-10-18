//
//  ViewController.h
//  Zadanie 3
//
//  Created by Dominik on 18/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController
@property (weak, nonatomic) IBOutlet UIButton *informationButton;
@property (weak, nonatomic) IBOutlet UIImageView *image;

- (IBAction)InformationClicked;

@end

