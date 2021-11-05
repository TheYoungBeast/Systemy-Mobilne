//
//  SecondViewController.h
//  Zadanie 4
//
//  Created by Dominik on 21/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>

NS_ASSUME_NONNULL_BEGIN

@interface SecondViewController : UIViewController<CLLocationManagerDelegate>
{
    CLLocationManager* locationManager;
    CLGeocoder* geocoder;
    CLPlacemark* placemark;
}
@property (weak, nonatomic) IBOutlet UILabel *latitudeLabel;
@property (weak, nonatomic) IBOutlet UILabel *longtitudeLabel;
@property (weak, nonatomic) IBOutlet UILabel *addressLabel;

@property (weak, nonatomic) IBOutlet UITextField *latitudeText;
@property (weak, nonatomic) IBOutlet UITextField *longtitudeText;
@property (weak, nonatomic) IBOutlet UITextView *addressText;

-(IBAction)getCurrentLocation:(id)sender;

@end

NS_ASSUME_NONNULL_END
