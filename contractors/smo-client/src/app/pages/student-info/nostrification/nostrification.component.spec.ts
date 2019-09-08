import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NostrificationComponent } from './nostrification.component';

describe('NostrificationComponent', () => {
  let component: NostrificationComponent;
  let fixture: ComponentFixture<NostrificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NostrificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NostrificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
