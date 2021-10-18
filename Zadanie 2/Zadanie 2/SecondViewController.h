//
//  SecondViewController.h
//  Zadanie 2
//
//  Created by Dominik on 17/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class SecondViewController;

@protocol SecondViewControllerDelegate <NSObject>

- (void) addItemViewController:(SecondViewController*) controller didFinishEnteringItem: (NSString*) item;

@end

@interface SecondViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *modifiedSurnameInputField;
@property (nonatomic, weak) id <SecondViewControllerDelegate> delegate;
@property NSString* surname;
@property (weak, nonatomic) IBOutlet UIButton *goBackButton;

- (IBAction)goBack;

@end

NS_ASSUME_NONNULL_END
