/* jQuery INFORMATION
   - Project : seller vdeal
   - Version : v1.0.0
   - Last Update : 14 January 2017
   - Copyright 2016-2017
*/

$(document).ready(function() {

	// Modal
	$('.modal').modal();

	// Select Option
	$('select').material_select();

	// Tooltip
	$('.tooltipped').tooltip({delay: 50});
    
    // Tabs
    $('div.tabs').tabs();
    
	// Auto Fill 
	$("[name='countries']").autocomplete({ // Countries
	    data: {
	        "Argentina": null,
			"Bahrain": null,
			"Canada": null,
			"Denmark": null,
			"Ecuador": null,
			"France": null,
			"Germany": null,
			"Honduras": null,
			"India ": null,
			"Japan": null
	    },
	    limit: 10,
	});

	$("[name='state']").autocomplete({ // State
	    data: {
	        "Arunachal Pradesh": null,
			"Assam": null,
			"Bihar": null,
			"Chhattisgarh": null,
			"Jharkhand": null,
			"Karnataka": null,
			"Kerala": null,
			"Madhya Pradesh": null,
			"Maharashtra": null
	    },
	    limit: 10,
	});

	$("[name='districts']").autocomplete({ // Districts
	    data: {
	        "Alappuzha": null,
			"Ernakulam": null,
			"Idukki": null,
			"Kannur": null,
			"Kasaragod": null,
			"Kollam": null,
			"Kottayam": null,
			"Kozhikode": null,
			"Malappuram ": null,
			"Palakkad": null,
			"Pathanamthitta": null,
			"Thiruvananthapuram": null,
			"Thrissur": null,
			"Wayanad": null
	    },
	    limit: 10,
	});

});



// login Submit
function logSubmit(){
	$.post("", {
		name: $('#userName').val(),
		password: $('#userPassword').val()
	}, function(data){
		alert('Login')
	});
}

// Forgot Password Input
function fogtPassword(){
	$(function (data){
		alert('Forgot your password');
	});
}

// Add Location Submit
function addLocation(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
		countries: $('#adCou').val(),
		state: $('#adSta').val(),
		districts: $('#adDis').val(),
		location: $('#adLoc').val()
    }, function(data){
		alert('Add Location')
	})
}

// Edit Location Submit
function edtLocation(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
		countries: $('#edtNum').val(),
		state: $('#edtSta').val(),
		districts: $('#edtDis').val(),
		location: $('#edtLoc').val()
    }, function(data){
		alert('Edit and Update Location')
	})
}

// Add Post Submit
function addPost(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
		countries: $('#adCou').val(),
		state: $('#adSta').val(),
		districts: $('#adDis').val(),
		subdistricts: $('#adSubdis').val(),
        village: $('#adVlg').val(),
        pin: $('#adPin').val(),
        post: $('#adPst').val()
    }, function(data){
		alert('Add Post')
	})
}

// Edit Post Submit
function edtPost(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
		countries: $('#edtCou').val(),
		state: $('#edtSta').val(),
		districts: $('#edtDis').val(),
		subdistricts: $('#edtSubdis').val(),
        village: $('#edtVlg').val(),
        pin: $('#edtPin').val(),
        post: $('#edtPst').val()
    }, function(data){
		alert('Edit and Update Post')
	})
}

// Add Critevia
function addCritevia(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        product: $('#adPro').val(),
        valtype: $('#adTyp').val()
    }, function(data){
        alert('Add Critevia')
    })
}

// Edit Critevia
function edtCritevia(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        product: $('#edtPro').val(),
        valtype: $('#edtTyp').val()
    }, function(data){
        alert('Edit And update Critevia')
    })
}

// Add CTV
function addCtv(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        ctvplan: $('#adCplan').val(),
        ctvamount: $('#adCamount').val(),
        ctvtown: $('#adCtown').val(),
        ctvdiscount: $('#adCdisc').val()
    }, function(data){
        alert('Add New CTV')
    })
}

// Edit CTV
function edtCtv(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        ctvplan: $('#edtCplan').val(),
        ctvamount: $('#edtCamount').val(),
        ctvtown: $('#edtCtown').val(),
        ctvdiscount: $('#edtCdisc').val()
    }, function(data){
        alert('Edit and Update CTV')
    })
}

// Add Plan 
function addPlan(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        planname: $('#adName').val(),
        planamount: $('#adAmount').val(),
        nousers: $('#adUsers').val(),
        plantype: $('#adType').val(),
        productvisible: $('#adPv').val(),
        productstore: $('#adPs').val(),
        freeproduct: $('#adFp').val(),
        townvisible: $('#adTv').val(),
        shoplisting: $('#adSlf').val(),
        coupenvisible: $('#adCv').val()
    }, function(data){
        alert('Add New Plan')
    })
}

// Edit Plan 
function edtPlan(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        planname: $('#edtName').val(),
        planamount: $('#edtAmount').val(),
        nousers: $('#edtUsers').val(),
        plantype: $('#edtType').val(),
        productvisible: $('#edtPv').val(),
        productstore: $('#edtPs').val(),
        freeproduct: $('#edtFp').val(),
        townvisible: $('#edtTv').val(),
        shoplisting: $('#edtSlf').val(),
        coupenvisible: $('#edtCv').val()
    }, function(data){
        alert('Edit and Update Plan')
    })
}

// Edit Plan Premium Customer
function edtPc(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        pcamount: $('#edtPcamount').val()
    }, function(data){
        alert('Edit and Update Preemium Customer')
    })
}

// Add User
function addUser(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        name: $('#adName').val(),
        address1: $('#adAdr1').val(),
        address2: $('#adAdr2').val(),
        place: $('#adPlace').val(),
        pin: $('#adPin').val(),
        usertype: $('#adUtype').val(),
        email: $('#adEmail').val()
    }, function(data){
        alert('Add New User')
    })
}

// Edit User
function edtUser(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        name: $('#edtName').val(),
        address1: $('#edtAdr1').val(),
        address2: $('#edtAdr2').val(),
        place: $('#edtPlace').val(),
        pin: $('#edtPin').val(),
        usertype: $('#edtUtype').val(),
        email: $('#edtEmail').val()
    }, function(data){
        alert('Edit and update User')
    })
}

// Add User Type 
function addUsertype(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        type: $('#adType').val(),
        supertype: $('#adSpt').val()
    }, function(data){
        alert('Add New User Type')
    })
}

// Edit User Type 
function edtUsertype(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        type: $('#edtType').val(),
        supertype: $('#edtSpt').val()
    }, function(data){
        alert('Edit and Update User Type')
    })
}

// Add Super Category 
function addSc(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        categoryname: $('#adCn').val()
    }, function(data){
        alert('Add New Super Category')
    })
}

// Edit Super Category 
function edtSc(){
    $.post("",{
        rolnumber: $('#rolNum').val(),
        categoryname: $('#edtCn').val(),
        onlinestatus: $('#edtOns').val(),
        offlinestatus: $('#edtOfs').val()
    }, function(data){
        alert('Edit and Update New Super Category')
    })
}
