$(document).ready(function(){
            //hide the error section
            $("#shipmentModeError").hide();
            $("#shipmentCodeError").hide();
            $("#enableShipmentError").hide();
            $("#shipmentGradeError").hide();
            $("#shipmentDescriptionError").hide();

            //define error variables
            var shipmentModeError=false;
            var shipmentCodeError=false;
            var enableShipmentError=false;
            var shipmentGradeError=false;
            var shipmentDescriptionError=false;

            //define validate functions
            function validate_shipmentMode(){//shipment mode
                var val=$("#shipmentMode").val();
                if(val==''){
                    $("#shipmentModeError").show();
                    $("#shipmentModeError").html("*Please select one <b>shipment mode</b>");
                    $("#shipmentModeError").css('color','red');
                    shipmentModeError=false;
                }else{
                    $("#shipmentModeError").hide();
                    shipmentModeError=true;
                }
                return shipmentModeError;
            }

            function validate_shipmentCode(){//shipment code
                var val=$("#shipmentCode").val();
                var exp=/^[A-Z0-9\.\-]{4,10}$/;
                if(val==''){
                    $("#shipmentCodeError").show();
                    $("#shipmentCodeError").html("*Please enter <b>shipment Code</b>");
                    $("#shipmentCodeError").css('color','red');
                    shipmentCodeError=false;
                }else if(!exp.test(val)){
                	$("#shipmentCodeError").show();
                    $("#shipmentCodeError").html("*Invalid <b>shipment Code</b>");
                    $("#shipmentCodeError").css('color','red');
                    shipmentCodeError=false;
                }
                else{
                    $("#shipmentCodeError").hide();
                    shipmentCodeError=true;
                }
                return shipmentCodeError;
            }

            function validate_enableShipment(){//enable shipment
                var val=$("[name='enableShipment']:checked").length;
                if(val==0){
                    $("#enableShipmentError").show();
                    $("#enableShipmentError").html("*Chose one option");
                    $("#enableShipmentError").css('color','red');
                    enableShipmentError=false;
                }else{
                    $("#enableShipmentError").hide();
                    enableShipmentError=true;
                }
                return enableShipmentError;
            }

            function validate_shipmentGrade(){//shipmentGrade
                var val=$("[name='shipmentGrade']:checked").length;
                if(val==0){
                    $("#shipmentGradeError").show();
                    $("#shipmentGradeError").html("*Chose one option");
                    $("#shipmentGradeError").css('color','red');
                    shipmentGradeError=false;
                }else{
                    $("#shipmentGradeError").hide();
                    shipmentGradeError=true;
                }
                return shipmentGradeError;
            }

            function validate_shipmentDescription(){
                var val=$("#shipmentDescription").val();
                var exp = /^[A-Za-z0-9\.\,\s\-]{2,100}$/;
                if(val==''){
                    $("#shipmentDescriptionError").show();
                    $("#shipmentDescriptionError").html("*Enter shipment Description");
                    $("#shipmentDescriptionError").css('color','red');
                    shipmentDescriptionError=false;
                }else if(!exp.test(val)){
                    $("#shipmentDescriptionError").show();
                    $("#shipmentDescriptionError").html("*Wrong shipment Description");
                    $("#shipmentDescriptionError").css('color','red');
                    shipmentDescriptionError=false;
                }
                else{  
                    $("#shipmentDescriptionError").hide();
                    shipmentDescriptionError=true;
                }
                return shipmentDescriptionError;
            }



            //bind with event 
            $("#shipmentMode").change(function(){
                validate_shipmentMode();
            });
            $("#shipmentCode").keyup(function () {
                $(this).val($(this).val().toUpperCase());
                validate_shipmentCode();
            });
            $("[name='enableShipment']").change(function(){
                validate_enableShipment();
            });
            $("[name='shipmentGrade']").change(function(){
                validate_shipmentGrade();
            });
            $("#shipmentDescription").keyup(function(){
                validate_shipmentDescription();
            });


            //implement on submit
            $("#shipRegForm").submit(function(){
                validate_shipmentMode();
                validate_shipmentCode();
                validate_enableShipment();
                validate_shipmentGrade();
                validate_shipmentDescription();
                if(shipmentCodeError && shipmentModeError && shipmentGradeError && enableShipmentError && shipmentDescriptionError){
                    return true;
                }else{
                    return false;
                }
            });

        });