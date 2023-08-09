package com.hogent.devOps_Android.network

class VmProperty {
    public List<ProjectenDto.Index> Projecten { get; set; } = new();
    {

        public int Id { get; set; }
        public String Name { get; set; }
        public User user { get; set; }
    }

    public int Total { get; set; }
}