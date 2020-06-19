
$(document).ready(function() {
   
   
    $('#wizardForm')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            // This option will not ignore invisible fields which belong to inactive panels
            excluded: ':disabled',
            fields: {
				
				/*first step validations*/
                quarter: {
                validators: {
                    notEmpty: {
                        message: 'The Quarter Name is required'
                    }
                }
            	},
                
                postcode: {
                validators: {
                    notEmpty: {
                        message: 'The First Name is required'
                    }
                }
                },
                visitdate: {
                    validators: {
                        notEmpty: {
                            message: 'The Visit Date is required'
                        }
                    }
                    },
                    endtime: {
                        validators: {
                            notEmpty: {
                                message: 'The End time is required'
                            }
                        }
                        },
                        dealername: {
                            validators: {
                                notEmpty: {
                                    message: 'The Dealer Name is required'
                                }
                            }
                            },
                            selectcountry: {
                                validators: {
                                    notEmpty: {
                                        message: 'The Country Name is required'
                                    }
                                }
                                },
                                visitday: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The Visit Day is required'
                                        }
                                    }
                                    },
                                    delaerlocation: {
                                        validators: {
                                            notEmpty: {
                                                message: 'The Dealer Location  is required'
                                            }
                                        }
                                        },
                                        brandname: {
                                            validators: {
                                                notEmpty: {
                                                    message: 'The Brand Name  is required'
                                                }
                                            }
                                            },
                                            calweek: {
                                                validators: {
                                                    notEmpty: {
                                                        message: 'The Week  is required'
                                                    }
                                                }
                                                },
                                                scname: {
                                                    validators: {
                                                        notEmpty: {
                                                            message: 'The SC Name  is required'
                                                        }
                                                    }
                                                    },
                                                    city: {
                                                        validators: {
                                                            notEmpty: {
                                                                message: 'The City Name  is required'
                                                            }
                                                        }
                                                        },
                                                        modelselect: {
                                                            validators: {
                                                                notEmpty: {
                                                                    message: 'The Model Name  is required'
                                                                }
                                                            }
                                                            },
                                                            contact: {
                                                                validators: {
                                                                    notEmpty: {
                                                                        message: 'The Contact  is required'
                                                                    }
                                                                }
                                                                },
                                                                designation: {
                                                                    validators: {
                                                                        notEmpty: {
                                                                            message: 'The Designation  is required'
                                                                        }
                                                                    }
                                                                    },
                                                                    buno: {
                                                                        validators: {
                                                                            notEmpty: {
                                                                                message: 'The Location ID/BU.N is required'
                                                                            }
                                                                        }
                                                                        },
                                                                        gender: {
                                                                            validators: {
                                                                                notEmpty: {
                                                                                    message: 'The Gender is required'
                                                                                }
                                                                            }
                                                                            },
                                                                            scdesc: {
                                                                                validators: {
                                                                                    notEmpty: {
                                                                                        message: 'The SC Description is required'
                                                                                    }
                                                                                }
                                                                                },
				
				/*Second step validations*/
				
				name: {
                validators: {
                    notEmpty: {
                        message: 'The Name is required'
                    }
                }
            	},
                
                
                email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                            message: 'The input is not a valid email address'
                        },

                }
            	},
                age: {
                    validators: {
                        notEmpty: {
                            message: 'The Age is required'
                        }
                    }
                    },
                    contact: {
                        validators: {
                            notEmpty: {
                                message: 'The Contact is required'
                            }
                        }
                        },
				 
				
                /*Third Step validation */
                brands: {
                    validators: {
                        notEmpty: {
                            message: 'The Brand is required'
                        }
                    }
                    },
                    models: {
                        validators: {
                            notEmpty: {
                                message: 'The Models is required'
                            }
                        }
                        },
                        contr: {
                            validators: {
                                notEmpty: {
                                    message: 'The Year is required'
                                }
                            }
                            },
                            		
                /*fourth Step validation */
                nameprovided: {
                    validators: {
                        notEmpty: {
                            message: 'The Provided Name is required'
                        }
                    }
                    },
                    pname: {
                        validators: {
                            notEmpty: {
                                message: 'The Name is required'
                            }
                        }
                        },
                        description: {
                            validators: {
                                notEmpty: {
                                    message: 'The Description is required'
                                }
                            }
                            },
				
            }
        })
        .bootstrapWizard({
            tabClass: 'nav nav-pills',
            onTabClick: function(tab, navigation, index) {
				
                return validateTab(index);
            },
            onNext: function(tab, navigation, index) {
                var numTabs    = $('#wizardForm').find('.tab-pane').length,
                    isValidTab = validateTab(index - 1);
                if (!isValidTab) {
                    return false;
                }

                if (index === numTabs) {
                    // We are at the last tab

                    // Uncomment the following line to submit the form using the defaultSubmit() method
                    // $('#installationForm').formValidation('defaultSubmit');

                    // For testing purpose
                   // $('#completeModal').modal();
                }

                return true;
            },
            onPrevious: function(tab, navigation, index) {
               // return validateTab(index + 1);
            },
            onTabShow: function(tab, navigation, index) {
                
               
                // Update the label of Next button when we are at the last tab
                var numTabs = $('#wizardForm').find('.tab-pane').length;
                 // console.log(numTabs)
                
                var status = ((index+1)/numTabs)*100;
                
                $('.progress-status').innerWidth(status+'%');
                console.log(status);
                
                $('#wizardForm')
                    .find('.next')
                        .removeClass('disabled')    // Enable the Next button
                        .find('a')
                        .html('Next');

                // You don't need to care about it
                // It is for the specific demo
                
            }
        });
    
    
    

    function validateTab(index) {
        var fv   = $('#wizardForm').data('formValidation'), // FormValidation instance
            // The current tab
            $tab = $('#wizardForm').find('.tab-pane').eq(index);

        // Validate the container
        fv.validateContainer($tab);

        var isValidStep = fv.isValidContainer($tab);
        if (isValidStep === false || isValidStep === null) {
            // Do not jump to the target tab
            return false;
        }

        return true;
    }
});