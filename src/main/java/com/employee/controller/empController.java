package com.employee.controller;

@Restcontroller
public class empController {


    @Autowired
    private empRepo empRepo;

    @GetMapping
    public ResponseEntity<List<emp> getAllemp() {
        try{
                List<Emp> empList = new ArrayList<>();
                empRepo.findAll().forEach(empList::add);

                if (empList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT )
                }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/getEmpByid/{id}")
    public ResponseEntity<Emp> getempByid(@PathVariable Long id) {
        Optional<emp> empData = empRepo.findByid(Id);

        if (empData.isPresent()) {
            return new ResponseEntity<>(empData.get(), HttpStatus.Ok);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @PostMapping("/addEmp")
    public void addEmp(@RequestBody Emp emp) {
        Emp empObj = empRepo.save(emp);

        return new ResponseEntity<>(empObj, HttpStatus.OK);

    }
    
    @PostMapping ("/updateEmpyid/{id}")
    public void updateEmpByid(@PathVariable Long id) {
       Optional<emp> oldempData = empRepo.findByid(id);

       if (empData.isPresent()) {
           Emp updatedEmpData = oldempData.get();
           updatedEmpData.setName(newEmpData.getName());
           updatedEmpData.setAddress(newEmpData.getAddress());
           updatedEmpData.setPhone(newEmpData.getPhone());
           updatedEmpData.setDOB(newEmpData.getDOB());
           updatedEmpData.setDepartment(newEmpData.getDepartment());
           updatedEmpData.setPosition(newEmpData.getPosition());
           updatedEmpData.setSex(newEmpData.getSex());

           Emp empObj = empRepo.save(updatedEmpData);
           return new ResponseEntity<>(empObj, HttpStatus.OK);
       } 
       
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/deleteEmpByid{id}")
    public ResponseEntity<HttpStatus> deletEmpbyid(@PathVariable Long id){
        empRepo.deleteEmpByid(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }



}
