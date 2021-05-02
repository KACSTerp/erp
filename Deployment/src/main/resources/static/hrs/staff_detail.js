//plain javscript ==================================
     //grab the things we need
 

    // vue ==========================================
    var app = new Vue({
      el: '#body',
      data: {
    	  employee:null,
    	  role: null,
    	  id: null,
    	  date: [ ],
        errors: {
          name: false,
          email: false
        }
      },
      mounted () {
    	  let vm = this;
    	  axios.all([
  	    		axios.get('/rest/role'),
				axios.get('/rest/id'),
  	    		axios.get('/rest/employeeInfo')
  	    	])
  	      .then(axios.spread(
  	    function (roleResponse, idResponse, employeeResponse) {
  	    	vm.employee = employeeResponse.data;
  	    	console.log(employeeResponse.data);
  	    	vm.role = roleResponse.data;
  	    	if(vm.role === "Managers" || vm.role === "HR")
                $("#base").attr("src", "../base.js");
              if(vm.role === "Employees")
                $("#base").attr("src", "../base_employee_role.js");
	    	  vm.id = idResponse.data
            
              console.log("length: " + (vm.employee).length);
              console.log("Date: " + vm.employee[0].p_DateOfBirth);

              for(var i = 0; i < (vm.employee).length; i++){
                var messageDate = vm.employee[i].p_DateOfBirth;
                var slicedDate = messageDate.split("T");
                vm.date.push(slicedDate[0]);
                var messageStartDate = vm.employee[i].p_StartDate;
                var slicedStartDate = messageStartDate.split("T");
                vm.date.push(slicedStartDate[0]);
              }
            })
  	    	)
    	  },
          methods: {
            seeID: function(event) {
            	targetId = event.currentTarget.id;
          	  console.log( {targetId: targetId} );
					
                   window.location.href = '/hrs/staff_detail.html';
               
              },
              seeBonus: function(event){
                targetId = event.currentTarget.id;
                console.log( {targetId: targetId} );
                axios.post('/rest/employeeBonusInfo',{
                  p_EmployeeID: parseInt(targetId)
                }).then(
                  window.location.href = '/hrs/staff_bonus_detail.html'
                )
              },
              delete_staff:function(event){
            	  targetId = event.currentTarget.id;
            	  axios.post('/rest/deleteEmployee',{
                      p_EmployeeID: parseInt(targetId)
                    })
              }
          }
    });