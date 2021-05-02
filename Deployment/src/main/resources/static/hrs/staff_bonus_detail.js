//plain javscript ==================================
     //grab the things we need
 var total = 0;

    // vue ==========================================
    var app = new Vue({
      el: '#body',
      data: {
    	  allBonus: null ,
    	  employee: null ,
    	  role: null,
    	  id: null,
    	  date: [ ],
        
      },
      mounted () {
    	  let vm = this;
    	    axios.all([
    	    	axios.get('/rest/employeeInfo'),
    	    	axios.get('/rest/ReadEmployeeBonuses'),
    	    	axios.get('/rest/role'),
       		  	axios.get('/rest/id')
    	    	])
    	      .then(axios.spread(
    	    	  function (employeeResponse, bonusRespones, roleResponse, idResponse) {
    	    	  vm.allBonus = bonusRespones.data
    	    	  vm.employee = employeeResponse.data
    	    	  vm.role = roleResponse.data
  	    	if(vm.role === "Managers" || vm.role === "HR")
                $("#base").attr("src", "../base.js");
              if(vm.role === "Employees")
                $("#base").attr("src", "../base_employee_role.js");
	    	  vm.id = idResponse.data
            
              console.log("length: " + (vm.allBonus).length);
              console.log("Date: " + vm.allBonus[0].p_Starting);

              for(var i = 0; i < (vm.allBonus).length; i++){
                var messageDate = vm.allBonus[i].p_Starting;
                var slicedDate = messageDate.split("T");
                vm.date.push(slicedDate[0]);
                var messageStartDate = vm.allBonus[i].p_Ending;
                var slicedStartDate = messageStartDate.split("T");
                vm.date.push(slicedStartDate[0]);
              }
            })
    	    	)
    	  },
    	  methods: {
    		  bonusResult: function(i , j){
    			  total = total + (i * j);
    			  return (i * j);
    		  },
    		  totalSalary: function (i){
    			  total = total + (i * 1);
    	  		return (total);
    	  	},
    	  	seeID: function(event) {
    	      	targetId = event.currentTarget.id;
    	    	  console.log( {targetId: targetId} );
    	    	  axios.post('/rest/getEmployeeID', {
    	    		  employeeID: this.id
    	            }).then(response => {
    	                window.location.href = '/hrs/staff_detail.html';
    	            })
    	        }
    	  }
    });